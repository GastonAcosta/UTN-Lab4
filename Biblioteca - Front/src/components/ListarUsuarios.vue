<template>
  <div class="listado-usuarios">
    <h1 align="center">Listado de Usuarios</h1>
    <div class="btn-back">
            <router-link to="/inicio" class="volver">Volver</router-link>
        </div>
    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Nombre</th>
          <th>Contraseña</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="usuario in usuarios" :key="usuario.id">
          <td>{{ usuario.id }}</td>
          <td>{{ usuario.nombre }}</td>
          <td>{{ usuario.contraseña }}</td>
          <td class="from-group">
            <button class="editar" @click="editarUsuario(usuario)">Editar</button>
            <button class="eliminar" @click="eliminarUsuario(usuario.id)">Eliminar</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="usuarioAEditar" class="editar-usuario">
      <h3>Editar Usuario</h3>
      <div class="form-group">
        <label>ID:</label>
        <input v-model="usuarioAEditar.id" disabled />
      </div>
      <div class="form-group">
        <label>Nombre:</label>
        <input v-model="usuarioAEditar.nombre" />
      </div>
      <div class="form-group">
        <label>Contraseña:</label>
        <input v-model="usuarioAEditar.contraseña" />
      </div>
      <button @click="actualizarUsuario">Actualizar</button>
      <button @click="cancelarEdicion">Cancelar</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      usuarios: [],
      usuarioAEditar: null,
    };
  },
  methods: {
    obtenerUsuarios() {
      this.$axios.get('http://localhost:8080/usuarios/listar')
        .then(response => {
          console.log('Respuesta de la API:', response.data);
          this.usuarios = response.data;
        })
        .catch(error => {
          console.error('Error al obtener usuarios:', error);
        });
    },
    editarUsuario(usuario) {
      this.usuarioAEditar= Object.assign({}, usuario);
    },
    actualizarUsuario() {
      this.$axios.put(`http://localhost:8080/usuarios/modificar/${this.usuarioAEditar.id}`, this.usuarioAEditar)
        .then(() => {
          this.obtenerUsuarios();
        });
      console.log('Usuario actualizado:', this.usuarioAEditar);
      this.usuarioAEditar = null;
    },
    cancelarEdicion() {
      this.usuarioAEditar = null;
    },
    eliminarUsuario(id) {
     if (window.confirm('¿Estás seguro de que deseas eliminar al usuario?')) {
        this.$axios.delete(`http://localhost:8080/usuarios/eliminar/${id}`)
          .then(response => {
            console.log('Usuario eliminado correctamente:', response);
          })
          .catch(error => {
            console.error('Error al eliminar el usuario:', error);
          });
      }
    },
  },
  mounted() {
    this.obtenerUsuarios();
  },
};
</script>

<style scoped>

.table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.table th, .table td {
  border: 1px solid #ddd;
  padding: 18px;
  text-align: left;
}

.table th {
  background-color: #f2f2f2;
}


.editar,
.eliminar {
  margin-right: 5px;
  padding: 3px 7px;
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

.editar-usuario {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
  width: 100%;
  max-width: 600px;
}

.form-group {
  margin-bottom: 15px;
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

.editar-usuario button:hover {
  background-color: #45a049;
}

.back-link {
    text-decoration: none;
    background-color: #4caf50;
    color: white;
    padding: 8px 16px;
    border-radius: 5px;
    transition: background-color 0.3s;
    margin-bottom: 10px;
}

.btn-back {
  margin-top: 90px;
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
