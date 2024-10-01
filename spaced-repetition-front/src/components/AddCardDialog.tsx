import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { Button } from "./Button";
import { useRef } from "react";
import { saveNewCard } from "../services/cards.service";

interface AddCardDialogProps {
  closeDialog: () => void;
}

export function AddCardDialog({ closeDialog }: AddCardDialogProps) {
  const frontInputRef = useRef<HTMLTextAreaElement>(null);
  const backInputRef = useRef<HTMLTextAreaElement>(null);

  const saveCard = async () => {
    if (!frontInputRef.current || !backInputRef.current) return;

    await saveNewCard(frontInputRef.current.value, backInputRef.current.value);

    frontInputRef.current.value = "";
    backInputRef.current.value = "";
  };

  return (
    <div className="w-screen h-screen absolute z-50 top-0 left-0 bg-black bg-opacity-75 flex justify-center items-center">
      <div className="relative rounded-md w-4/6 h-2/3 bg-white opacity-100 shadow-lg flex flex-col justify-start items-start py-4 px-6">
        <header className="flex flex-row justify-between w-full mb-10">
          <h1 className="font-semibold text-lg">Add a new card</h1>
          <button onClick={closeDialog} className="text-xl">
            <FontAwesomeIcon icon={faXmark} />
          </button>
        </header>

        <section className="w-full flex flex-col gap-10">
          <div>
            <label htmlFor="front-card">Front</label>
            <textarea
              id="front-card"
              placeholder="Write a text for the front side of the card"
              ref={frontInputRef}
              rows={2}
              className="w-full bg-gray-100 p-2 rounded-sm border border-gray-200 outline-gray-300"
            />
          </div>

          <div>
            <label htmlFor="back-card">Back</label>
            <textarea
              id="back-card"
              placeholder="Write a text for the back side of the card"
              ref={backInputRef}
              rows={2}
              className="w-full bg-gray-100 p-2 rounded-sm border border-gray-200 outline-gray-300"
            />
          </div>
        </section>

        <footer className="w-full flex flex-row justify-end absolute bottom-0 m-auto right-5 gap-5 mt-10 mb-5">
          <Button
            onClick={closeDialog}
            label="cancel"
            className="bg-red-500 hover:bg-red-600"
          />
          <Button
            onClick={saveCard}
            label="save"
            className="bg-green-500 hover:bg-green-600"
          />
        </footer>
      </div>
    </div>
  );
}
