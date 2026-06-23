import { Routes } from '@angular/router';
import { Admin } from './components/admin/admin';
import { Home } from './components/home/home';
import { Login} from './components/login/login';
import { Register } from './components/register/register';
import { Shop } from './components/shop/shop';
import { Cart } from './components/cart/cart';
import { Checkout} from './components/checkout/checkout';
import { Orders } from './components/orders/orders';
import { Feedback} from './components/feedback/feedback';
import { Contact} from './components/contact/contact';
import { ChangePassword } from './components/change-password/change-password';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'shop', component: Shop },
  { path: 'cart', component: Cart },
  { path: 'checkout', component: Checkout},
  { path: 'orders', component: Orders },
  { path: 'feedback', component: Feedback },
  { path: 'contact', component: Contact },
  { path: 'change-password', component: ChangePassword },
  { path:'admin',  component:Admin },

  { path: '**', redirectTo: '' }
];