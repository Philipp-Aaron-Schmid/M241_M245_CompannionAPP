// Importiert React und die benötigten Ressourcen
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider, } from "react-router-dom";

// Importiere Navigation (Seiten, Komponenten)
import Login from "./pages/login";
import Private from "./pages/private";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />
  },
  {
    path: "/private",
    loader: private_loader,
    element: <Private />
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
