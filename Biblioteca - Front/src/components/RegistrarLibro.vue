<template>
    <div>
        <h2 align="center">Registro de Libros</h2>
        <div class="btn-back">
            <router-link to="/inicio" class="volver">Volver</router-link>
        </div>
    </div>
    <div class="libros-container">
        <div class="nuevo-libro">
            <h3>Registrar Nuevo Libro</h3>
            <div class="form-group">
                <label for="titulo">Título:</label>
                <input v-model="nuevoLibro.titulo" placeholder="Título" id="titulo" />
            </div>

            <div class="form-group">
                <label for="autor">Autor:</label>
                <input v-model="nuevoLibro.autor" placeholder="Autor" id="autor" />
            </div>

            <div class="form-group">
                <label for="genero">Género:</label>
                <input v-model="nuevoLibro.genero" placeholder="Género" id="genero" />
            </div>

            <div class="form-group">
                <label for="cantPaginas">Cantidad de páginas:</label>
                <input v-model="nuevoLibro.cantPaginas" placeholder="Cantidad de páginas" id="cantPaginas" />
            </div>

            <div class="form-group">
                <label for="sinopsis">Sinopsis:</label>
                <input v-model="nuevoLibro.sinopsis" placeholder="Sinopsis" id="sinopsis" />
            </div>

            <button class="agregar" @click="crearLibro">Registrar</button>
            
            <div v-if="registroExitoso" class="confirmacion">Libro registrado
            </div>
        </div>
    </div>
</template>
  
  
<script>
export default {
    data() {
        return {
            libros: [],
            nuevoLibro: { id: null, titulo: '', autor: '', genero: '', cantPaginas: null, sinopsis: '' },
            registroExitoso: false,
        };
    },
    methods: {
        obtenerLibros() {
            this.$axios.get('http://localhost:8080/libros/traer')
                .then(response => {
                    console.log('Respuesta de la API:', response.data);
                    this.libros = response.data;
                });
        },

        crearLibro() {
            console.log('Nuevo libro a agregar:', this.nuevoLibro);
            this.$axios.post('http://localhost:8080/libros/crear', this.nuevoLibro)

                .then(() => {
                    this.obtenerLibros();
                    this.nuevoLibro = { titulo: '', autor: '', genero: '', cantPaginas: null, sinopsis: '' };
                    this.registroExitoso = true;
                })

                .catch(error => {
                    console.error('Error al agregar libro:', error);
                    console.log('Respuesta del servidor:', error.response);
                    console.log('Data del error:', error.data);
                    console.log('Datos enviados desde Vue.js:', this.nuevoLibro);
                    console.log('Configuración de la solicitud:', error.config);

                });
        },

        created() {
            this.obtenerLibros();
        },
    }
}

</script>
<style scoped>
.libros-container {
    font-family: Arial, sans-serif;
    background-color: #f7f7f7;
    min-height: 40vh;
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}

.nuevo-libro {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    margin-bottom: 0px;
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

.agregar {
    text-decoration: none;
    background-color: green;
    color: white;
    padding: 5px 12px;
    border-radius: 5px;
    transition: background-color 0.3s;
}

.confirmacion {
    text-decoration: none;
    background-color: green;
    color: white;
    padding: 5px 12px;
    border-radius: 5px;
    transition: background-color 0.3s;
}


.back-link {
    text-decoration: none;
    background-color: #4caf50;
    color: white;
    padding: 8px 16px;
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
  
  