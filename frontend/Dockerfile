# FROM node:24-alpine3.23
# WORKDIR /opt/server
# COPY package*.json ./
# RUN npm install
# COPY . .
# EXPOSE 4200
# CMD ["npm", "start"]

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

# ---------- Stage 1 : Build ----------
FROM node:24-alpine3.23 AS builder
#directory to place code
WORKDIR /opt/server
#copy files in to this path ,if anything change in future package.json unchanged so it reuse npm install
COPY package*.json ./
#
RUN npm install
#copy rest of files
COPY . .
#it will compile everything 
RUN npm run build

# ---------- Stage 2 : Runtime ----------We dont need node angular
FROM nginx:alpine

COPY --from=builder /opt/server/dist/flower-shop-ui/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]