import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { Role } from 'src/app/domain/role/models/role';
import { RoleRepository } from 'src/app/domain/role/role.repository';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.scss'],
  encapsulation: ViewEncapsulation.None,

})
export class RoleComponent implements OnInit {
  rolesForm!: FormGroup
  size: number = 10;
  page: number = 0;
  totaItem:number = 0
  allRoles:Role[] = []
  isRoleButtonVisible: boolean = true
  roleSubmit: boolean = false
  currentRoleData!:Role
  displayedColumns:string[] = [
    'id',
    'arabicName',
    'englishName',
    'code',
    'actions'
  ]


  constructor(
    private roleRepository: RoleRepository,
    private build: FormBuilder,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private translate: TranslateService,

  ) { }

  ngOnInit(): void {
    this.roleForm()
    this.getAllRoles()
  }

  
roleForm(){
  this.rolesForm = this.build.group({
    id:[''],
    arabicName:['',[Validators.required,Validators.maxLength(50)]],
    englishName:['',[Validators.maxLength(50)]],
    code:['',[Validators.maxLength(50)]]
  })
 }

 getAllRoles(): void {
  this.roleRepository.getList({  page: this.page,size: this.size,}).subscribe((result) => {
    this.allRoles = result.data;
    this.totaItem = result.pagination.itemCount;
  });
}

onPaginationChange(event: PageEvent) {

  this.page  = event.pageIndex;
  this.size = event.pageSize;
  this.getAllRoles();
}

updateRole() {
  this.isRoleButtonVisible = true;
  this.roleSubmit = true;
  this.roleRepository.update(this.rolesForm.value).subscribe(
    () => {
      this.getAllRoles();
      this.roleSubmit = false;
      this.snackBar.open(
        this.translate.instant('roles.updated-successfully'),
        this.translate.instant('roles.close'),
        {
          duration: 2000,
        }
      );
    },
    () => {
      this.roleSubmit = false;
    }
  );
}

addRole() {
  this.isRoleButtonVisible = true;
  this.roleSubmit = true;
  this.roleRepository.add(this.rolesForm.value).subscribe(
    () => {
      this.getAllRoles();
      this.roleSubmit = false;
      this.snackBar.open(
        this.translate.instant('roles.added-successfully'),
        this.translate.instant('roles.close'),
        {
          duration: 2000,
        }
      );
    },
    () => {
      this.roleSubmit = false;
    }
  );
}

deleteRole(role: Role) {
  this.roleRepository.delete(role.id).subscribe(() => {
    this.getAllRoles();
    this.snackBar.open(
      this.translate.instant('roles.deleted-successfully'),
      this.translate.instant('roles.close'),
      {
        duration: 2000,
      }
    );
  });
}

openConfirmationDialog(role: Role) {
  let dialogRef = this.dialog.open(ConfirmDialogComponent);
  dialogRef.afterClosed().subscribe((result) => {
    if (result === 'yes') {
      this.deleteRole(role);
    }
  });
}

onRolesSubmit(){
  this.rolesForm.markAllAsTouched();
   if (this.rolesForm.valid) {
     this.rolesForm.controls['id'].value
       ? this.updateRole()
       : this.addRole();
     this.rolesForm.reset();
   }
}

resetRolesForm(){
  this.rolesForm.controls['id'].value
     ? this.fetchData(this.currentRoleData)
     : this.rolesForm.reset();
}

fetchData(role: Role): void {
  this.isRoleButtonVisible = false;
  this.rolesForm.patchValue(role);
  this.currentRoleData = role;
}

restartRoleForm(): void {
  this.rolesForm.reset();
}

}
