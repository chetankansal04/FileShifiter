import Navbar from "@/components/Navbar";
import "@/styles/globals.css";
import { parseCookies } from "nookies";

export default function App({ Component, pageProps}) {

  const cookies = parseCookies();
  const isLoggedIn = cookies.token;

  return (
    <main className="flex flex-col w-full pt-20">
      <Navbar isLoggedIn={isLoggedIn}/>
      <Component {...pageProps} />
    </main>
  );
}

