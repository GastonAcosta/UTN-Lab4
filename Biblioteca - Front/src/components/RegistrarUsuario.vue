<template>
    <div>
        <h2 align="center">Usuarios</h2>
        <div class="btn-back">
            <router-link to="/inicio" class="volver">Volver</router-link>
        </div>
    </div>
    <div class="registrar-usuario-container">
      <div class="nuevo-usuario">
        <h3>Registro de Usuarios</h3>
        <div class="form-group">
          <label for="nombre">Nombre de usuario:</label>
          <input v-model="nuevoUsuario.nombre" placeholder="Nombre de usuario" id="nombre" />
        </div>
  
        <div class="form-group">
          <label for="contrasena">Contraseña:</label>
          <input v-model="nuevoUsuario.contraseña" type="password" placeholder="Contraseña" id="contrasena" />
        </div>
  
        <button class="registrar" @click="registrarUsuario">Registrar</button>
        <div v-if="registroExitoso" class="confirmacion">
          ¡Registro exitoso! El usuario {{ nuevoUsuario.nombre }} ha sido registrado.
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        nuevoUsuario: { nombre: '', contraseña: '' },
        usuarios: [],
        registroExitoso: false,
      };
    },
    methods: {
      obtenerUsuarios() {
        this.$axios.get('http://localhost:8080/usuarios/traer')
          .then(response => {
            console.log('Respuesta de la API:', response.data);
            this.usuarios = response.data;
          });
      },
      registrarUsuario() {
        console.log('Nuevo usuario a registrar:', this.nuevoUsuario);
        this.$axios.post('http://localhost:8080/usuarios/registrar', this.nuevoUsuario)
          .then(() => {
            this.obtenerUsuarios();
            this.nuevoUsuario = { nombre: '', contraseña: '' };
            this.registroExitoso = true;
            setTimeout(() => {
              this.registroExitoso = false;
            }, 5000);
          })
          .catch(error => {
            console.error('Error al agregar usuario:', error);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .registrar-usuario-container {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    min-height: 100vh;
    padding: 50px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
  }
  
  .nuevo-usuario {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
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
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
    margin-bottom: 10px;
  }
  
  .registrar {
    text-decoration: none;
    background-color: green;
    color: white;
    padding: 5px 12px;
    border-radius: 5px;
    transition: background-color 0.3s;
  }
  
  .registrar:hover {
    background-color: #45a049;
  }
  .confirmacion {
    text-decoration: none;
    background-color: green;
    color: white;
    padding: 5px 12px;
    border-radius: 5px;
    transition: background-color 0.3s;
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
  
