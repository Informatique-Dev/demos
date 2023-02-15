export interface Employee {
  data:DataEmployee[]
   pagination:{
    currentPage:number;
    itemCount:number;
    size:number;
    totalPages:number;

   }

}
export interface DataEmployee {

  id: number;
  version: number;
  fullName: string;
  nickName: string;
  nationalId: string;
  primaryPhoneNo: string;
  secondaryPhoneNo: string;
  address: string;
  job: string;
}
