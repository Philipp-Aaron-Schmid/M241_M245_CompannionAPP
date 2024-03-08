// Importiert React und die benötigten Ressourcen
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider, } from "react-router-dom";

// Importiere Navigation (Seiten, Komponenten)
import Login from "./pages/login";
/* import Private from "./pages/private"; */
import Admin from "./pages/admin";
import Tasks from "./component/tasks";
import CreateTasks from "./component/createTasks";
import UpdateTasks from "./component/updateTasks";

const router = createBrowserRouter([ 
  {
    path: "/",
    element: <Login />
  },
/*   {
    path: "/private",
    element: <Private />
  }, */
  {
    path: "/admin",
    element: <Admin />
  },
  {
    path: "/tasks",
    element: <Tasks />
  },
  {
    path: "/createTasks",
    element: <CreateTasks />
  },
  {
    path: "/updateTasks/:id",
    element: <UpdateTasks />
  }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
