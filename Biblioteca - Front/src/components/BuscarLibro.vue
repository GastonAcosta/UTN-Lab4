<template>
  <div>
      <h2 align="center">Buscar Libro</h2>
      <div class="btn-back">
        <router-link to="/inicio" class="volver">Volver</router-link>
      </div>
    <!-- Contenido del componente -->
    <div class="libros-container">
    </div>
    <div class="container">
      <form @submit.prevent="buscarLibro" class="mb-4">
        <div class="search-container">
          <div class="mb-3">
            <label for="titulo" class="form-label">Título del Libro:</label>
            <input v-model="titulo" id="titulo" class="form-control input-lg" placeholder="Ingrese el título del libro" />
          </div>
          <div class="button-container">
            <button type="submit" class="btn btn-primary btn-lg">Buscar</button>
          </div>
        </div>
      </form>
      <div v-if="libroEncontrado" class="result-container">
        <h3 class="mb-3">Resultado de la Búsqueda</h3>
        <table class="table">
          <tbody>
            <tr>
              <th scope="row">Id</th>
              <td>{{ libroEncontrado.id }}</td>
            </tr>
            <tr>
              <th scope="row">Título</th>
              <td>{{ libroEncontrado.titulo }}</td>
            </tr>
            <tr>
              <th scope="row">Autor</th>
              <td>{{ libroEncontrado.autor }}</td>
            </tr>
            <tr>
              <th scope="row">Género</th>
              <td>{{ libroEncontrado.genero }}</td>
            </tr>
            <tr>
              <th scope="row">Páginas</th>
              <td>{{ libroEncontrado.cantPaginas }}</td>
            </tr>
            <tr>
              <th scope="row">Sinopsis</th>
              <td>{{ libroEncontrado.sinopsis }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-if="error" class="alert alert-danger mt-4">
        <p>Error al buscar el libro: {{ error }}</p>
      </div>

      <div v-if="!libroEncontrado && !error" class="no-result-message mt-4">
        <p>No se encontró ningún libro</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      titulo: '',
      libroEncontrado: null,
      error: null,
    };
  },

  methods: {
    limpiarCampos() {
      this.titulo = '';
      this.libroEncontrado = null;
      this.error = null;
    },

    buscarLibro() {
      if (this.titulo.trim() === '') {
        this.error = 'Ingrese el titulo:';
        return;
      }

      this.$axios.get(`http://localhost:8080/libros/buscarNombre/${this.titulo}`)
        .then(response => {
          this.libroEncontrado = response.data;
          this.error = null;
        })
        .catch(error => {
          console.error('Error al buscar libros:', error);
          this.libroEncontrado = null;
          this.error = 'Error al buscar libros';
        });
    },
  },
};
</script>

<style scoped>
.libros-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 70vh;
}

.search-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.result-container {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  margin-top: 20px;
}

.input-lg {
  margin: 10px;
}

.alert-danger {
  background-color: white;
  border-color: black;
  color: red;
}
.table tbody tr {
  display: flex;
  align-items: center;
  margin-top: 10px;
  border-color: black;
  border-radius: 10px;
}

.table th,
.table td {
  margin: 5px;
}
.table th {
  text-align: left;
}

.no-result-message {
  text-align: center;
  color:darkslategrey;
}

.button-container {
  display: flex;
  gap: 10px;
}

.volver {
  text-decoration: none;
  background-color: green;
  color: white;
  padding: 5px 12px;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.volver:hover {
  background-color: green;
}
</style>
