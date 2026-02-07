import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/ProductService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product',
  imports: [CommonModule],
  standalone: true, 
  templateUrl: './product.html',
  styleUrl: './product.css',
})
export class ProductComponent implements OnInit {


  products: any[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.findAllProducts().subscribe((data) => {
      this.products = data;
    });
  }

}
