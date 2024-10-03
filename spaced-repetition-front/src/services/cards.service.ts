import axios, { AxiosResponse } from "axios";
import { Card } from "../types/Card";

const BASE_PATH = "http://localhost:3300";

export const fetchCardsToReview = async (): Promise<AxiosResponse<Card[]>> => {
  const response = await axios(`${BASE_PATH}/card/cards-to-review`, {
    method: "GET",
    headers: { "Content-Type": "application/json"}
  });

  return response;
};

export const saveNewCard = async (frontText: string, backText: string) => {
  var card = { front: frontText, back: backText };

  await axios(`${BASE_PATH}/card`, {
    method: "POST",
    headers: { "Content-Type": "application/json"},
    data: JSON.stringify(card),
  });
};

export const reviewCard = async (id: number, optionChoose: number) => {
  await axios(`${BASE_PATH}/card/review-card/id/${id}/next-review/${optionChoose}`, {
    method: "POST",
  });
}