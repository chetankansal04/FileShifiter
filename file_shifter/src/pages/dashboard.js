import Image from "next/image";
import { useAuthGaurd } from "../../logic/useAuthGaurd";
import { ChevronsDown } from "@geist-ui/icons";

export default function Dashboard() {
  useAuthGaurd();
  return (
      <div className="flex flex-col ">
        <div className="p-container flex px-20 pt-10 justify-center max-w-full 
          h-full cursor-default ">
          <div className="text-container flex  flex-col justify-center  gap-5 ">
            <h1 className="main-heading">From Anything to Everything</h1>
            <p className="sub-heading">
              Your digital documents, effortlessly organized. We make managing
              files simple, so you can focus on what matters.
            </p>
          </div>
          <Image
          className="min-w-[600px]"
            src="/images/workbench.png"
            alt="image"
            width={700}
            height={300}
            priority={true}
          ></Image>
        </div>
        <div className="arrow_container flex justify-center items-center">
        <ChevronsDown size={50} />
        </div>
      </div>
    );
}
