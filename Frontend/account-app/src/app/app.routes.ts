import { Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InvoiceDetailsComponent } from './invoice-details/invoice-details.component';

export const routes: Routes = [
{path:'', redirectTo:'index', pathMatch:'full'},
{path:'index', component:IndexComponent},
{path:'login', component:LoginComponent},
{path:'register', component:RegisterComponent},
{path:'dashboard', component:DashboardComponent},
{path:'invoice-details', component:InvoiceDetailsComponent}
];
