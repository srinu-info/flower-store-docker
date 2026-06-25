# FROM node:24-alpine3.23
# WORKDIR /opt/server
# COPY package*.json ./
# RUN npm install
# COPY . .
# EXPOSE 4200
# CMD ["npm", "start"]

FROM node:24-alpine3.23 AS builder
WORKDIR /opt/server
COPY package*.json ./
COPY . .
RUN npm install

FROM node:24-alpine3.23
WORKDIR /opt/server
COPY --from=builder /opt/server /opt/server
EXPOSE 4200
CMD ["npm","start"]