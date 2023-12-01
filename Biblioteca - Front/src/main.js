import { createApp } from 'vue';
import App from './App.vue';
import axios from 'axios';
import { createRouter, createWebHistory } from 'vue-router';
import BuscarLibro from './components/BuscarLibro.vue';
import Inicio from './components/Inicio.vue';
import RegistrarUsuario from './components/RegistrarUsuario.vue';
import ListarUsuarios from './components/ListarUsuarios.vue';
import RegistrarLibro from './components/RegistrarLibro.vue';
import ListarLibros from './components/ListarLibros.vue';
import RegistrarPrestamo from './components/RegistrarPrestamo.vue';
import ListarPrestamos from './components/ListarPrestamos.vue';



axios.defaults.baseURL = 'http://localhost:8080'; 
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

const app = createApp(App);


app.config.globalProperties.$axios = axios;


const routes = [
  { path: '/buscarlibro', component: BuscarLibro },
  { path: '/inicio', component: Inicio },
  { path: '/registrarusuario', component: RegistrarUsuario },
  { path: '/listarusuarios', component: ListarUsuarios },
  { path: '/registrarlibro', component: RegistrarLibro },
  { path: '/listarlibros', component: ListarLibros },
  { path: '/registrarprestamo', component: RegistrarPrestamo },
  { path: '/listarprestamos', component: ListarPrestamos },

 
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

app.use(router);

app.mount('#app');