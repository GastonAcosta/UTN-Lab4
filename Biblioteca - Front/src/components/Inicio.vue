<template>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <h1 align="center">Biblioteca</h1>

  <nav class="navbar navbar-expand-lg navbar-light bg-black">
  <div class="collapse navbar-collapse justify-content-center" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item mr-2">
        <div class="nav-section">
        <a href="Inicio" @click="toggleSection('Inicio')" class="nav-link">Inicio</a>
        
      </div>
      </li>
      
      <li class="nav-item mr-2">
        <div class="nav-section">
        <a href="#" @click="toggleSection('Libro')" class="nav-link">Libro</a>
        <ul v-show="secciones['Libro']">
          <li><router-link to="registrarlibro">Registrar</router-link></li>
          <li><router-link to="listarlibros">Listado</router-link></li>
          <li><router-link to="buscarlibro">Buscar</router-link></li>
        </ul>
      </div>
      </li>
      <li class="nav-item mr-2">
        <div class="nav-section">
        <a href="#" @click="toggleSection('Prestamo')" class="nav-link">Prestamo</a>
        <ul v-show="secciones['Prestamo']">
          <li><router-link to="registrarprestamo">Registrar</router-link></li>
          <li><router-link to="listarprestamos">Listado</router-link></li>
          <li><router-link to="editarprestamo"></router-link></li>
        </ul>
      </div>
      </li>
      <li class="nav-item mr-2">
        <div class="nav-section">
        <a href="#" @click="toggleSection('Usuario')" class="nav-link">Usuario</a>
        <ul v-show="secciones['Usuario']">
          <li><router-link to="registrarusuario">Registrar</router-link></li>
          <li><router-link to="listarusuarios">Listado</router-link></li>
        </ul>
      </div>
      </li>
    </ul>
  </div>
</nav>

</template>


<script>
export default {
  data() {
    return {
      usuario: '',
      secciones: {
        'Inicio': false,
        'Libro': false,
        'Prestamo': false,
        'Usuario': false,
      },
    };
  },
  mounted() {
    this.usuario = localStorage.getItem('usuario');
  },
  methods: {
    toggleSection(section) {
      this.secciones[section] = !this.secciones[section];
    },
  },
};
</script>

<style scoped>

  .navbar {
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: #4caf50;
    color: black;
    padding: 10px;
  }

  .nav-section {
    text-align: center;
    margin-top: 50px;
    flex: 1;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin-right: 10px;
  }

  a {
    text-decoration: none;
    color: white;
    font-weight: bold;
  }

  a:hover {
    color:aqua;
  }
  .nav-section h2 {
    margin-bottom: 5px;
  }

ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: block;
    margin-bottom: 30px;
  }

  .nav-link {
    font-size: 30px;
  }
  
</style>
 

   

  