import { useLoaderData } from "react-router-dom";
import { getPrivateContent } from "../functions/getContent";

export async function loader() {
  const data = await getPrivateContent();
  return { data };
}

export default function Private() {
  const { data } = useLoaderData();

  return (
    <>
      <h1>Privat!</h1>
      <p>Hier sind die Serverdaten: {data}</p>
    </>
  );
}
