export const environment = {
  production: false,
  apiUrl: 'http://54.221.191.219:8080'
};

// for i in api-gateway auth-service product-service cart-service order-service feedback-service frontend
// do
//     docker build -t devopsrinu/$i:v1 ./$i
// done