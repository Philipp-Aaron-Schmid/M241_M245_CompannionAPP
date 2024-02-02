// Importiert React und die benötigten Ressourcen
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider, } from "react-router-dom";

// Importiere Navigation (Seiten, Komponenten)
import Login from "./pages/login";

const router = createBrowserRouter([ 
  
  {
    path: "/",

    element: <Login />
  }
  
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
