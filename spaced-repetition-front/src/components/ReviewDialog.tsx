import { useState } from "react";
import { Card } from "../types/Card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import { Button } from "./Button";
import { reviewCard } from "../services/cards.service";

interface ReviewDialogProps {
  cards: Card[];
  endReview: () => void;
}

export function ReviewDialog({ cards, endReview }: ReviewDialogProps) {
  const [cardInView, setCardInView] = useState<Card>(cards[0]);
  const [showBackCard, setShowBackCard] = useState<boolean>(false);

  const nextCard = async (optionChoose: number) => {
    await reviewCard(cardInView.id, optionChoose);

    const index = cards.indexOf(cardInView);

    if (cards.length - 1 < index + 1) return endReview();

    setShowBackCard(false);
    setCardInView(cards[index + 1]);
  };

  return (
    <div className="w-screen h-screen absolute z-50 top-0 left-0 bg-black bg-opacity-75 flex justify-center items-center">
      <div className="relative rounded-md w-4/6 h-1/2 bg-white opacity-100 shadow-lg flex flex-col justify-start items-start py-4 px-6">
        <header className="flex flex-row justify-between w-full mb-10">
          <h1 className="font-semibold text-lg">Card #{cardInView.id}</h1>
          <button onClick={endReview} className="text-xl">
            <FontAwesomeIcon icon={faXmark} />
          </button>
        </header>

        <p>{cardInView.front}</p>
        {showBackCard && (
          <>
            <div className="h-px w-full my-4 bg-black self-center"></div>
            <p>{cardInView.back}</p>
          </>
        )}

        <footer className="w-full flex flex-row justify-center absolute bottom-0 m-auto right-0 left-0 gap-5 mt-10">
          {showBackCard ? (
            <>
              <div className="flex flex-col mb-3">
                <Button onClick={() => nextCard(cardInView.badOption)} label="hard" className="bg-yellow-500 hover:bg-yellow-600" />
                <span className="text-xs self-center">
                  {cardInView.badOption} day(s)
                </span>
              </div>
              <div className="flex flex-col">
                <Button onClick={() => nextCard(cardInView.goodOption)} label="easy" className="bg-green-500 hover:bg-green-600" />
                <span className="text-xs self-center">
                  {cardInView.goodOption} day(s)
                </span>
              </div>
            </>
          ) : (
            <div className="mb-5">
              <Button onClick={() => setShowBackCard(true)} label="show response" className="bg-blue-500 hover:bg-blue-600" />
            </div>
          )}
        </footer>
      </div>
    </div>
  );
}
