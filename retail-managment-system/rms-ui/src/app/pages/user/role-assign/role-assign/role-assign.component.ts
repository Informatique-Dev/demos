import { Component, OnInit } from '@angular/core';
import { UserRole, User } from 'src/app/domain/user/models/user';
import { UserRepository } from 'src/app/domain/user/user.repository';
import { ActivatedRoute } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Employee } from './../../../../domain/employee/models/employee';
import { EmployeeRepository } from './../../../../domain/employee/employee.repository';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { Role } from 'src/app/domain/role/models/role';
import { RoleRepository } from 'src/app/domain/role/role.repository';

@Component({
  selector: 'app-role-assign',
  templateUrl: './role-assign.component.html',
  styleUrls: ['./role-assign.component.scss']
})
export class RoleAssignComponent implements OnInit {

  allRolesById:UserRole[] = []
  userById:User[]=[]
  size: number = 10;
  page: number = 0;
  totaItem: number = 0;
  isUserRoleButtonVisible : boolean = true
  currentUserRoleData! : UserRole
  assignUserRoleForm! : FormGroup
  employees:Employee[] = []
  allRoles:Role[] = []
  userRoleSubmit:boolean = false
  currentData!: UserRole
  submit:boolean = false
  displayedColumns:string[] = [
    'id',
    'roles',
    'actions'
  ]
  activeID = this.activatedRoute.snapshot.params['id']
  userName:string = ''
  fullName: string = ''
  constructor(
    private userRepository : UserRepository,
    private activatedRoute : ActivatedRoute,
    private build : FormBuilder,
    private employeeRepository: EmployeeRepository,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private translate: TranslateService,
    private roleRepository: RoleRepository
  ) { 

  }

  ngOnInit(): void {
    this.getAllRolesById()
    this.getUserById()
    this.assignRoleForm()
    this.getAllEmployees()
    this.getAllRoles()
  }

  assignRoleForm(){
    this.assignUserRoleForm = this.build.group({
      id:[''],
      role:['']
    })
  }

  getAllRolesById(){
    this.userRepository.getListById(`${this.activeID}/role`).subscribe((result:any)=>{
      this.allRolesById = result.data
      this.totaItem = result.pagination.itemCount;
    })
  }

  getUserById(){
    this.userRepository.get(this.activeID).subscribe((result:any)=>{
      this.userById = result
      this.fullName = result.employee.fullName
      this.userName = result.userName
    })
  }

  getAllEmployees(): void {
    this.employeeRepository.getList().subscribe((result) => {
      this.employees = result.data;
    });
  }

  getAllRoles(): void {
    this.roleRepository.getList({  page: this.page,size: this.size,}).subscribe((result) => {
      this.allRoles = result.data;
    });
  }

  assignRoleToUser(){
    this.isUserRoleButtonVisible = true;
   this.submit = true;
   this.userRepository.assignRole(this.assignUserRoleForm.value,this.activeID).subscribe(
     () => {
       this.getAllRolesById();
       this.submit = false;
       this.snackBar.open(
         this.translate.instant('user.assigned-role-successfully'),
         this.translate.instant('user.close'),
         {
           duration: 2000,
         }
       );
     },
     () => {
       this.submit = false;
     }
   );
  }

  deleteRoleFromUser(role: UserRole){
    this.userRepository.deleteRole(role.id).subscribe(() => {
      this.getAllRolesById();
      this.snackBar.open(
        this.translate.instant('user.deleted-role-successfully'),
        this.translate.instant('user.close'),
        {
          duration: 2000,
        }
      );
    });
  }

  onPaginationChange(event: PageEvent) {

    this.page  = event.pageIndex;
    this.size = event.pageSize;
    this.getAllRolesById();
  }

  fetchUserRoleData(user : UserRole):void{
    this.isUserRoleButtonVisible = false;
    this.assignUserRoleForm.patchValue(user);
    this.currentUserRoleData = user;
  }

  openConfirmationDialog(userRole : UserRole){
    let dialogRef = this.dialog.open(ConfirmDialogComponent);
   dialogRef.afterClosed().subscribe((result) => {
     if (result === 'yes') {
       this.deleteRoleFromUser(userRole);
     }
   });
  }

  compareEmployeeFn(a: Employee, b: Employee){
    if(!a || !b) return false
    return a.id === b.id
  }

  compareRolesFn(a:Role, b:Role){
    if(!a || !b) return false
    return a.id === b.id
  }

  onSubmit(){
    this.assignUserRoleForm.markAllAsTouched();
   if (this.assignUserRoleForm.valid) {
     this.assignRoleToUser()
     this.assignUserRoleForm.reset()
   }
  }

}
