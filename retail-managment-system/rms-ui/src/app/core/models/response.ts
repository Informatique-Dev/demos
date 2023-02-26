import { Pagination } from "./pagination";
export interface Response<T>{
  data:T[];
  pagination: Pagination;
}