// import {
//   HttpInterceptorFn
// } from '@angular/common/http';

// export const authInterceptor:
// HttpInterceptorFn =
// (req, next) => {

//   const token =
//     localStorage.getItem('token');

//   if (
//     token &&
//     req.url.includes('/admin/')
//   ) {

//     const cloned =
//       req.clone({

//         setHeaders: {

//           Authorization:
//             `Bearer ${token}`

//         }

//       });

//     return next(cloned);

//   }

//   return next(req);

// };

import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const token = localStorage.getItem('token');
 console.log("================================");
  console.log("METHOD :", req.method);
  console.log("URL    :", req.url);
  if (
    token &&
    !req.url.includes('/auth/login') &&
    !req.url.includes('/auth/register')
  ) {
     console.log("Adding Token");
    req = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

  }

  return next(req);

};