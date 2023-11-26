<p align="center">
    <img src="icon.png" width="100%">
</p>

<h2 align="center"> 
    🎮 A series of educational minigames for kids with disabilities at Instituto Nuevo Amanecer
<h2>

<p align="center">
    <a href="https://github.com/prettier/prettier">
        <img alt="code style: prettier" src="https://img.shields.io/badge/code_style-prettier-ff69b4.svg?style=flat-square">
    </a>
</p>

# 📚 Usage

## 🤖 Android

Clone the repo, create a new firebase storage project and put the `google-services.json` file inside the `/frontend/app` directory.

Then build and run it on an emulator (recommended `Pixel C API 34`)

## 💻 Server

Make sure you have `node` installed.

Create a `.env` file inside the `/backend` folder with all the credentials of firebase:

```env
FIREBASE_API_KEY=...
FIREBASE_AUTH_DOMAIN=...
FIREBASE_PROJECT_ID=...
FIREBASE_STORAGE_BUCKET=...
FIREBASE_MESSAGING_SENDER_ID=...
FIREBASE_APP_ID=...
```

Then install all dependencies

```console
npm install
```

For **dev** mode:

```console
npm run dev
```

```console
npm run db:studio
```

For **production** mode:

```console
npm run build
```

```console
npm start
```

# ⚒️ Tech

- Node.js
- TypeScript
- SQLite
- Native Android

# Development Team

**Team iOS**

- Pedro Alonso Moreno Salcedo [@pedroalonsoms](https://github.com/pedroalonsoms)
- Felipe [@Felipegonac0](https://github.com/Felipegonac0)
- Franco [@Peco1503](https://github.com/Peco1503)
- André [@Galindo4007](https://github.com/Galindo4007)
- Yuvan Thirukumaran [@YuvanTec](https://github.com/YuvanTec)

# TODO
- [ ] Drag & Drop (Yuvan)
- [ ] Comunicador (Franco)
- [ ] Subir imagenes a firebase (Galindo)
- [ ] Dropdown categorias para subir imagenes (Felipe)
- [ ] Quitar el harcodeo del usuario al subir la imagenes a firebase (Felipe)
- [ ] Mensaje de error 500 en el dialogo de errro (Galindo y Pedro)
- [ ] Integrar el comunicador con el back (Franco y Yuvan)
- [ ] Dropdown list para el correo del papá (Galindo y Felipe)
- [ ] Dropdown list con el nombre para seleccionar el alumno (Galindo)
- [ ] Corregir las diapositivas
- [ ] Agregar botón para ir atrás (Pedro)
- [ ] Estilos del dropdown en `selecciona tu nombre` (Galindo)
- [ ] Hacer fetch en pantalla de `Mi hij@` (Galindo)
- [ ] Endpoint para info del papá para galindo (Pedro)
- [ ] Hacer fetch en pantalla de los detalles del alumno para el papá (Galindo)
- [ ] Lock level access based for students based on the allowed one (Yuvan y Franco)
- [ ] Fix UI polish, corner radious, puntos negros de la contraseña, botones de atras, font del mensaje de dialogo (Pedro)