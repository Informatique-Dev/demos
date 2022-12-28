import { Category } from '../../category/models/category';

export interface Product {
  id: number;
  version: number;
  name: string;
  modelNo: string;
  brand: string;
  cashPrice: number;
  quantity: number;
  productCategoryDto: Category;
}
