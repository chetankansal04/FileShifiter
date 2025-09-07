import Navbar from "@/components/Navbar";
import "@/styles/globals.css";

export default function App({ Component, pageProps}) {

  return (
    <main className="flex flex-col w-full">
      <Navbar />
      <Component {...pageProps} />
    </main>
  );
}

