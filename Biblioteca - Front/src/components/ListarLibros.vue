<template>
  <div class="listalib">
    <h1 align="center">Listado de Libros</h1>
    <div class="btn-back">
    <router-link to="/inicio" class="volver">Volver</router-link>
  </div>
    <table>
      <thead>
        <tr>
          <th>Id</th>
          <th>Título</th>
          <th>Autor</th>
          <th>Género</th>
          <th>Páginas</th>
          <th>Sinopsis</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="libro in libros" :key="libro.id">
          <td>{{ libro.id }}</td>
          <td>{{ libro.titulo }}</td>
          <td>{{ libro.autor }}</td>
          <td>{{ libro.genero }}</td>
          <td>{{ libro.cantPaginas }}</td>
          <td>{{ libro.sinopsis }}</td>
          <td class="acciones">
            <button class="editar" @click="ediLibro(libro)">Editar</button>
            <button class="eliminar" @click="eliminarLibro(libro.id)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="libroMod" class="edilib">
      <h3>Editar Libro</h3>
      <form @submit.prevent="modificarLibro">
        <div class="form-group">
          <label for="titulo">Título:</label>
          <input v-model="libroMod.titulo" id="titulo" />
        </div>
        <div class="form-group">
          <label for="autor">Autor:</label>
          <input v-model="libroMod.autor" id="autor" />
        </div>
        <div class="form-group">
          <label for="genero">Género:</label>
          <input v-model="libroMod.genero" id="genero" />
        </div>
        <div class="form-group">
          <label for="cantPaginas">Páginas:</label>
          <input v-model="libroMod.cantPaginas" id="cantPaginas" />
        </div>
        <div class="form-group">
          <label for="sinopsis">Sinopsis:</label>
          <textarea v-model="libroMod.sinopsis" id="sinopsis"></textarea>
        </div>
        <div class="form-group">
          <button type="submit">Actualizar</button>
          <button type="button" @click="cancelarEdicion">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
  <div v-if="msjE" class="msj">
    Modificación exitosa
  </div>

</template>

<script>
export default {
  data() {
    return {
      libros: [],
      libroMod: null,
      msjE: false
    };
  },
  methods: {
    obtenerLibros() {
      this.$axios.get('http://localhost:8080/libros/listar')
        .then(response => {
          console.log('Respuesta de la API:', response.data);
          this.libros = response.data;
        });
    },

    ediLibro(libro) {
      this.libroMod = Object.assign({}, libro);
    },

    modificarLibro() {
      this.$axios.put(`http://localhost:8080/libros/modificar/${this.libroMod.id}`, this.libroMod)
        .then(() => {
          this.obtenerLibros();
          this.libroMod = null;
          this.msjE = true;
        });
    },

    cancelarEdicion() {
      this.libroMod = null;
    },

    eliminarLibro(id) {
      if (window.confirm('¿Estás seguro de que deseas eliminar este libro?')) {
        this.$axios.delete(`http://localhost:8080/libros/eliminar/${id}`)
          .then(response => {
            console.log('Libro eliminado correctamente:', response);
            this.libros = this.libros.filter(libro => libro.id !== id);
          })
          .catch(error => {
            console.error('Error al eliminar el libro:', error);
          });
      }
    },
  },
  created() {
    this.obtenerLibros();
  },

}
</script>

<style scoped>
.listalib {
  max-width: 100%;
  max-height: 100%;
  margin: 20px auto;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
  border-color: black;
}

th,
td {
  border: 1px solid #ddd;
  padding: 6px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

button {
  margin-right: 5px;
  padding: 5px 10px;
  font-size: 0.9em;
  border: none;
  cursor: pointer;
  border-radius: 3px;
  transition: background-color 0.2s;
}

.editar {
  background-color: yellow;
  color: black;
  border: solid
}

.editar:hover {
  background-color: yellow;
}

.eliminar {
  background-color: red;
  color: white;
}

.eliminar:hover {
  border: solid;
  background-color:red;
}


r {
  display: flex;
  justify-content: space-between;
}

th,
td {
  flex: 1;
  padding: 8px;
  text-align: left;
}

.acciones {
  display: flex;
  gap: 15px;
  flex: 1;
  justify-content: flex-end;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
}

.form-group input {
    width: 70%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 6px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input,
textarea {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  margin-bottom: 10px;
}

.form-actions {
  display: flex;
  justify-content: space-between;
  margin-top: 15px;
}

.msj {
  background-color: green;
  color: white;
  padding: 6px;
  border-radius: 5px;
  margin-top: 10px;
  text-align: center;
  display: inline-block;
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

