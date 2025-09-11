import Image from "next/image";
import { ChevronsDown } from "@geist-ui/icons";
import HomeStartButton from "./HomeStartButton";

export default function PublicPage() {
  return (
    <div className="flex flex-col px-20 pb-20 min-h-full gap-10">
      <div
        className="p-container flex justify-center w-full items-center 
        h-full cursor-default "
      >
        <div className="text-container flex  flex-col justify-center  gap-5 ">
          <h1 className="main-heading">From Anything to Everything</h1>
          <p className="sub-heading">
            Your digital documents, effortlessly organized. We make managing
            files simple, so you can focus on what matters.
          </p>
        </div>
        
        <Image
          className="min-w-[500px]"
          src="/images/workbench.png"
          alt="image"
          width={600}
          height={300}
          priority={true}
        ></Image>
      </div>
      <div className="homebtn_container flex justify-center items-center">
        {/* <ChevronsDown size={50} /> */}
        <HomeStartButton />
      </div>
    </div>
  );
}
