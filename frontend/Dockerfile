FROM node:24-alpine3.23
WORKDIR /opt/server
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 4200
CMD ["npm", "start"]

# FROM node:24-alpine3.23 AS builder
# WORKDIR /opt/server
# COPY package*.json ./
# COPY . .
# RUN npm install

# FROM node:24-alpine3.23
# WORKDIR /opt/server
# COPY --from=builder /opt/server /opt/server
# EXPOSE 4200
# CMD ["npm","start"]

# # ---------- Stage 1 : Build ----------
# FROM node:24-alpine3.23 AS builder

# WORKDIR /opt/server

# COPY package*.json ./

# RUN npm install

# COPY . .

# RUN npm run build

# # ---------- Stage 2 : Runtime ----------
# FROM nginx:alpine

# COPY --from=builder /opt/server/dist/flower-shop-ui/browser /usr/share/nginx/html

# EXPOSE 80

# CMD ["nginx", "-g", "daemon off;"]