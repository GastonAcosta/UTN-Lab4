<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>

import BuscarLibro from './components/BuscarLibro.vue';
import Inicio from './components/Inicio.vue';
import RegistrarUsuario from './components/RegistrarUsuario.vue';
import ListarUsuarios from './components/ListarUsuarios.vue';
import RegistrarLibro from './components/RegistrarLibro.vue';
import ListarLibros from './components/ListarLibros.vue';
import RegistrarPrestamo from './components/RegistrarPrestamo.vue';
import ListarPrestamos from './components/ListarPrestamos.vue';

export default {
  name: 'App',
  components: {
    BuscarLibro,
    Inicio,
    RegistrarUsuario,
    ListarUsuarios,
    RegistrarLibro,
    ListarLibros,
    RegistrarLibro,
    RegistrarPrestamo,
    ListarPrestamos,
  }
};
</script>