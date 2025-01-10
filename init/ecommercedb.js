db.createCollection('ecommercedb', { capped: false });
db.ecommercedb.insert([
{
"name": "Teclado",
"description": "Marca DELL",
"price": 99.99,
"stock": 50
},
{
"name": "Computadora",
"description": "Marca DELL",
"price": 99.99,
"stock": 50
},
{
"name": "Mouse",
"description": "Marca DELL",
"price": 15.5,
"stock": 20
},
{
"name": "CPU",
"description": "Marca DELL",
"price": 150,
"stock": 10
},
{
"name": "Botella de agua",
"description": "Marca Cielo",
"price": 0,
"stock": 0
}
]);
