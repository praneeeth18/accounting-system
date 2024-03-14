import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-index',
  standalone: true,
  imports: [RouterLink],
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
