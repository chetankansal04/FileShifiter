import Navbar from "@/components/Navbar";
import "@/styles/globals.css";
import { parseCookies } from "nookies";

export default function App({ Component, pageProps, isLoggedIn }) {
  return (
    <main className="flex flex-col w-full pt-20">
      <Navbar isLoggedIn={isLoggedIn} />
      <Component {...pageProps} />
    </main>
  );
}

App.getInitialProps = async ({ ctx }) => {
  const cookies = parseCookies(ctx);
  const isLoggedIn = !!cookies.jwtToken;

  return {
    pageProps: {},
    isLoggedIn,
  };
};
