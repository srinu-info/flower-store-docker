export const environment = {
  production: false,
  apiUrl: '/api'
};

// for i in api-gateway auth-service product-service cart-service order-service feedback-service frontend
// do
//     docker build -t devopsrinu/$i:v1 ./$i
// done