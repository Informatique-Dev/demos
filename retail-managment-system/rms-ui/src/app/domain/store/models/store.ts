import { Employee } from "../../employee/models/employee"

export interface Store {
    id:number
    version:number
    name:string
    address:string
    responsible:Employee
}
