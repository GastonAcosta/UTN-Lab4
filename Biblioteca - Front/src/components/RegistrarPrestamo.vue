<template>
    <div>
        <h2 align="center">Registro de Prestamos</h2>
        <div class="btn-back">
            <router-link to="/inicio" class="volver">Volver</router-link>
        </div>
        <div class="prestamo-container">
            <div class="nuevo-prestamo">
                <h3>Registrar Nuevo Préstamo</h3>
                <div class="form-group">
                    <label for="fechaPrestamo">Fecha de Préstamo:</label>
                    <input type="date" v-model="nuevoPrestamo.fechaPrestamo" id="fechaPrestamo" />
                </div>

                <div class="form-group">
                    <label for="fechaDevolucion">Fecha de Devolución:</label>
                    <input type="date" v-model="nuevoPrestamo.fechaDevolucion" id="fechaDevolucion" />
                </div>

                <div v-for="(libro, index) in nuevoPrestamo.libros" :key="index">
                    <div class="form-group">
                        <label :for="'id' + index">ID del Libro </label>
                        <select v-model="libro.id" @change="cargarTituloLibro(index)" :id="'id' + index">
                            <option v-for="libroOption in libros" :key="libroOption.id" :value="libroOption.id">
                                {{ libroOption.id }}
                            </option>
                        </select>
                    </div>

                   
                </div>
                <button class="agregar" @click="registrarPrestamo">Registrar Prestamo</button>

                <div v-if="registroExitoso" class="confirmacion">Prestamo Registrado</div>
            </div>
        </div>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
            prestamos: [],
            libros: [],
            nuevoPrestamo: {
                idPrestamo: null,
                fechaPrestamo: '',
                fechaDevolucion: '',
                libros: [{ id_libro: ''}],
            },
            registroExitoso: false,
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
        obtenerLibros() {
            this.$axios.get('http://localhost:8080/libros/listar')
                .then(response => {
                    console.log('Respuesta de la API (Libros):', response.data);
                    this.libros = response.data;
                });
        },
        registrarPrestamo() {
            console.log('Nuevo préstamo a registrar:', this.nuevoPrestamo);
            this.$axios.post('http://localhost:8080/prestamos/crear', this.nuevoPrestamo)
                .then(response => {
                    console.log('Respuesta del servidor:', response.data);

                    this.prestamos.push(response.data);
                    this.registroExitoso = true;

                    setTimeout(() => {
                        this.registroExitoso = false;
                    }, 3000);

                    this.nuevoPrestamo = {
                        idPrestamo: null,
                        fechaPrestamo: '',
                        fechaDevolucion: '',
                        libro: [{ idLibro: ''}],
                    };
                })
                .catch(error => {
                    console.error('Error al registrar préstamo:', error);
                    console.log('Error completo:', error.response);
                });

        },
    },

    created() {
        this.obtenerPrestamos();
        this.obtenerLibros();
    },

};
</script>
  
<style scoped>
.prestamo-container {
    font-family: Arial, sans-serif;
    background-color: white;
    min-height: 100vh;
    padding: 50px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}

.nuevo-prestamo {
    background-color: white;
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

.form-group input,
.form-group select {
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

.agregar:hover {
    background-color:greenyellow;
}

.confirmacion {
    background-color: #ffffff;
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
  