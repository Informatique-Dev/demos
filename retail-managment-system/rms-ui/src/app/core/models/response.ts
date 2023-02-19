import { Pagination } from "./pagination";
export interface response<T>{
  data: [T];
  pagination: Pagination;
}