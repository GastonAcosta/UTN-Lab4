<template>
    <div class="lista-prestamos">
      <h1 align="center">Listado de Préstamos</h1>
      <div class="btn-back">
        <router-link to="/inicio" class="volver">Volver</router-link>
      </div>
      <table class="prestamos-table">
  <thead>
    <tr>
      <th>Id</th>
      <th>Fecha de Préstamo</th>
      <th>Fecha de Devolución</th>
      <th>Id Libro</th>
      <th>Acciones</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="prestamo in prestamos" :key="prestamo.id">
      <td>{{ prestamo.id }}</td>
      <td>{{ prestamo.fechaPrestamo }}</td>
      <td>{{ prestamo.fechaDevolucion }}</td>
      <td>{{ prestamo.libro ? prestamo.libro.id : 'N/A' }}</td>
      <td>
        <button class="editar" @click="editarPrestamo(prestamo)">Editar</button>
        <button class="eliminar" @click="eliminarPrestamo(prestamo.id)">Eliminar</button>
      </td>
    </tr>
  </tbody>
</table>
    </div>

    <div v-if="prestamoAEditar" class="editar-prestamo">
        <div class="formulario-container">
            <h3>Editar Préstamo</h3>
            <div class="form-group">
                <label for="fechaPrestamo">Fecha de Préstamo:</label>
                <input v-model="prestamoAEditar.fechaPrestamo" id="fechaPrestamo" type="date" />
            </div>
            <div class="form-group">
                <label for="fechaDevolucion">Fecha de Devolución:</label>
                <input v-model="prestamoAEditar.fechaDevolucion" id="fechaDevolucion" type="date" />
            </div>
            <div class="acciones">
                <button @click="actualizarPrestamo">Actualizar</button>
                <button @click="cancelarEdicion">Cancelar</button>
            </div>
            <div v-if="msjE" class="msj">{{ mensajeExito }}</div>
        </div>
    </div>
</template>
    
<script>
export default {
    data() {
        return {
            prestamos: [],
            prestamoAEditar: null,
            libros: [],
            mensajeExito: null,
        };
    },

    methods: {
        obtenerPrestamos() {
            this.$axios.get('http://localhost:8080/prestamos/listar')
                .then(response => {
                    console.log('Respuesta de la API:', response.data);
                    this.prestamos = response.data;
                });
        },

        cargarListaLibros() {
            this.$axios.get('http://localhost:8080/libros/listar')
                .then(response => {
                    this.libros = response.data;
                })
                .catch(error => {
                    console.error('Error al obtener la lista de libros:', error);
                });
        },

        editarPrestamo(prestamo) {
            this.prestamoAEditar = JSON.parse(JSON.stringify(prestamo));
        },

        actualizarPrestamo() {
            console.log('Datos a actualizar:', this.prestamoAEditar);


            this.$axios.put(`http://localhost:8080/prestamos/modificar/${this.prestamoAEditar.id}`, this.prestamoAEditar)
                .then(() => {
                    this.obtenerPrestamos();
                    this.prestamoAEditar = null;
                    this.mensajeExito = '¡Préstamo editado correctamente!';
                    setTimeout(() => {
                        this.mensajeExito = null;
                    }, 3000);
                })
                .catch(error => {
                    console.error('Error al actualizar el préstamo:', error);
                    console.log('Respuesta del servidor:', error.response);
                });
        },

        cancelarEdicion() {
            this.prestamoAEditar = null;
        },

        eliminarPrestamo(id) {
            if (window.confirm('¿Estás seguro de que deseas eliminar este préstamo?')) {
                this.$axios.delete(`http://localhost:8080/prestamos/eliminar/${id}`)
                    .then(response => {
                        console.log('Préstamo eliminado correctamente:', response);
                        this.obtenerPrestamos();
                    })
                    .catch(error => {
                        console.error('Error al eliminar el préstamo:', error);
                    });
            }
        },
    },

    created() {
        this.obtenerPrestamos();
        this.cargarListaLibros();
    },
};
</script>

  
<style scoped>
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

.lista-prestamos {
    margin-top: 20px;
}

form-group {
    margin-bottom: 15px;
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

.lista-prestamos {
    margin-top: 10px;
}

.editar,
.eliminar {
    padding: 5px 10px;
    font-size: 0.9em;
    border: none;
    cursor: pointer;
    border-radius: 3px;
    transition: background-color 0.2s;
    margin-right: 5px;
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

.prestamos-table {
    width: 100%;
    margin-top: 20px;
    border-collapse: collapse;
}

th,
td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

.editar-prestamo {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
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

.editar-prestamo {
    display: flex;
    justify-content: flex-end;
    padding: 20px;
}

.formulario-container {
    width: 100%;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

.back-link {
    text-decoration: none;
    background-color: #4caf50;
    color: white;
    padding: 8px 16px;
    border-radius: 5px;
    transition: background-color 0.3s;
    margin-top: 10px;
}

.btn-back {
    margin-top: 40px;
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
  
  
  