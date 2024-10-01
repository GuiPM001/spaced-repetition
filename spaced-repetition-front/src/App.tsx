import React, { useEffect, useState } from "react";
import { Card } from "./types/Card";
import { fetchCardsToReview } from "./services/cards.service";
import { ReviewDialog } from "./components/ReviewDialog";
import { Button } from "./components/Button";
import { AddCardDialog } from "./components/AddCardDialog";

function App() {
  const [cards, setCards] = useState<Card[]>([]);
  const [reviewInProcess, setReviewInProcess] = useState<boolean>(false);
  const [showAddCardDialog, setShowAddCardDialog] = useState<boolean>(false);

  useEffect(() => {
    getCards();
  }, [showAddCardDialog]);

  const getCards = async () => {
    const response = await fetchCardsToReview();
    setCards(response.data);
  };

  const endReview = () => {
    getCards();
    setReviewInProcess(false);
  };

  return (
    <div className="w-screen h-screen flex justify-start items-center flex-col p-10">
      <header className="font-bold text-3xl mb-10">
        <h1>SPACED REPETITION</h1>
      </header>

      <main>
        <section className="flex flex-col items-center gap-2">
          <p className="mb-4">
            Total cards to review:
            <span className="font-semibold"> {cards.length} </span>
          </p>

          <Button
            onClick={() => setShowAddCardDialog(true)}
            label="add card"
            className="bg-green-500 hover:bg-green-600"
          />
          <Button
            onClick={() => setReviewInProcess(true)}
            label="start review"
            className="bg-blue-500 hover:bg-blue-600"
          />
        </section>

        {cards.length > 0 && reviewInProcess && (
          <ReviewDialog cards={cards} endReview={endReview} />
        )}

        {showAddCardDialog && (
          <AddCardDialog closeDialog={() => setShowAddCardDialog(false)} />
        )}
      </main>
    </div>
  );
}

export default App;
