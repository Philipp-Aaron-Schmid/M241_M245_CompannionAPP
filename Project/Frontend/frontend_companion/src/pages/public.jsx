import { useLoaderData } from "react-router-dom";
import { getPublicContent } from "../functions/getContent";

export async function loader() {
  const data = await getPublicContent();
  return { data };
}

export default function Public() {
  const { data } = useLoaderData();

  return (
    <>
      <h1>Public</h1>
      <p>Hier sind die Serverdaten:</p>
      <ol>
        {data.map((item) => (
          <li key={item}> {item} </li>
        ))}
      </ol>
    </>
  );
}
