import PublicPage from "@/components/PublicPage";
import { GetServerSideProps } from "next";

export default function Home() {
  return (
    <div className=" flex min-h-dvh justify-center items-center ">
      <PublicPage />
    </div>
  );
}


