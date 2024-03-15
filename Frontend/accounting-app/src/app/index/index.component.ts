import { Component } from '@angular/core';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrl: './index.component.css'
})
export class IndexComponent {
  isSignInFormVisible: boolean = false;
  heading="Empowering Your Financial Journey"
  imgPath="../assets/images/figma1.jpg"
  heading1="Simplify, Analyze, Thrive!"
  toggleSignInForm(): void {
    this.isSignInFormVisible = !this.isSignInFormVisible;
  }
}
