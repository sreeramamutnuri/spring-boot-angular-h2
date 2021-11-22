import { Category } from './category';
export interface Product {
    id: number;
    name: String;
    price: number;
    imageUrl: String;
    description: String;
    category: {
        [key: string]: Category
    };

}