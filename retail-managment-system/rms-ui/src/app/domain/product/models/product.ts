import { temporaryCat } from "../../temporary cat/models/temporary-cat";

export interface Product {
  id: number;
  version: number;
  name: string;
  modelNo: string;
  brand: string;
  cashPrice: number;
  quantity: number;
  productCategoryDto: temporaryCat;
}
