import { Employee } from 'src/app/domain/employee/models/employee';
import { Role } from '../../role/models/role';

export interface User {
    id:number
    version:number
    userName:string
    password:string
    employee:Employee
}

export interface UserRole {
    id: number,
    version: number,
    user: User,
    role: Role
}
