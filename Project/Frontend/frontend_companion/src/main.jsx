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
import Users from "./component/users";
import CreateUsers from "./component/createUsers";
import UpdateUsers from "./component/updateUsers";

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
  },
  {
    path: "/users",
    element: <Users />
  },
  {
    path: "createUsers",
    element: <CreateUsers />
  },
  {
    path: "updateUsers/:id",
    element: <UpdateUsers />
  }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
