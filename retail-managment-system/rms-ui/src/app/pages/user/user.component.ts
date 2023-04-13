import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { EmployeeRepository } from 'src/app/domain/employee/employee.repository';
import { Employee } from 'src/app/domain/employee/models/employee';
import { User } from 'src/app/domain/user/models/user';
import { UserRepository } from 'src/app/domain/user/user.repository';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
  encapsulation: ViewEncapsulation.None,

})
export class UserComponent implements OnInit {

  allUsers: User[] = [];
  size: number = 10;
  page: number = 0;
  totaItem: number = 0;
  usersForm!: FormGroup;
  employees: Employee[] = [];
  userSubmit: boolean = false
  currentUserData!: User;
  isUserButtonVisible: boolean = true
  displayedColumns:string[]=[
    'id',
    'userName',
    'employee',
    'role',
    'actions'
  ]

  constructor(
    private userRepository: UserRepository,
    private employeeRepository: EmployeeRepository,
    private build: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.getAllUsers();
    this.getAllEmployees();
    this.userForm();
    console.log(this.allUsers)
  }

 userForm() {
    this.usersForm = this.build.group({
      id: [''],
      userName: ['', [Validators.required,Validators.maxLength(16)]],
      password: [''],
      employee: [''],
      role:['']
    });
  }

 getAllUsers(): void {
   this.userRepository.getList({  page: this.page,size: this.size,}).subscribe((result:any) => {
     this.allUsers = result.data;
     console.log(this.allUsers)
     this.totaItem = result.pagination.itemCount;
       });
 }

 onPaginationChange(event: PageEvent) {

   this.page  = event.pageIndex;
   this.size = event.pageSize;
   this.getAllUsers();
 }

 

 fetchUserData(user: User): void {
   this.isUserButtonVisible = false;
   this.usersForm.patchValue(user);
   this.currentUserData = user;
 }

 updateUser() {
   this.isUserButtonVisible = true;
   this.userSubmit = true;
   this.userRepository.update(this.usersForm.value).subscribe(
     () => {
       this.getAllUsers();
       this.userSubmit = false;
       this.snackBar.open(
         this.translate.instant('user.updated-successfully'),
         this.translate.instant('user.close'),
         {
           duration: 2000,
         }
       );
     },
     () => {
       this.userSubmit = false;
     }
   );
 }

 addUser() {
   this.isUserButtonVisible = true;
   this.userSubmit = true;
   this.userRepository.add(this.usersForm.value).subscribe(
     () => {
       this.getAllUsers();
       this.userSubmit = false;
       this.snackBar.open(
         this.translate.instant('user.added-successfully'),
         this.translate.instant('user.close'),
         {
           duration: 2000,
         }
       );
     },
     () => {
       this.userSubmit = false;
     }
   );
 }

 getAllEmployees(): void {
   this.employeeRepository.getList().subscribe((result) => {
     this.employees = result.data;
     console.log(this.employees)
   });
 }

 onUsersSubmit() {
   this.usersForm.markAllAsTouched();
   if (this.usersForm.valid) {
     this.usersForm.controls['id'].value
       ? this.updateUser()
       : this.addUser();
     this.usersForm.reset();
   }
 }

 resetUsersForm() {
   this.usersForm.controls['id'].value
     ? this.fetchUserData(this.currentUserData)
     : this.usersForm.reset();
 }
 compareFn(a: Employee, b: Employee) {
   if (!a || !b) return false;
   return a.id === b.id;
 }

 restartForm(): void {
   this.usersForm.reset();
 }

 openConfirmationDialog(user: User) {
   let dialogRef = this.dialog.open(ConfirmDialogComponent);
   dialogRef.afterClosed().subscribe((result) => {
     if (result === 'yes') {
       this.deleteUser(user);
     }
   });
 }

 deleteUser(user: User) {
   this.userRepository.delete(user.id).subscribe(() => {
     this.getAllUsers();
     this.snackBar.open(
       this.translate.instant('user.deleted-successfully'),
       this.translate.instant('user.close'),
       {
         duration: 2000,
       }
     );
   });
 }

navigateUserRole(id:number){
  this.router.navigate(['/user',id])
}
}
