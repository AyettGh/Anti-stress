import React, { useEffect, useState } from "react";

const Chat = () => {
  const [messages, setMessages] = useState([
    { from: "bot", text: "Hi there! How can I assist you today?" },
  ]);
  const [input, setInput] = useState("");

  const handleSend = async (e) => {
    e.preventDefault();
    if (!input.trim()) return;

    const userMessage = { from: "user", text: input };
    setMessages((prev) => [...prev, userMessage]);
    setInput("");

    try {
      const response = await fetch(
        `http://localhost:8080/chat?prompt=${encodeURIComponent(input)}`
      );
      const data = await response.text();

      setMessages((prev) => [...prev, { from: "bot", text: data }]);
    } catch (error) {
      setMessages((prev) => [
        ...prev,
        { from: "bot", text: "Error connecting to backend." },
      ]);
    }
  };

  return (
    <div className="max-w-screen-md mx-auto p-4">
      <h2 className="text-xl font-bold mb-4">ChatBot</h2>
      <div className="border p-4 rounded h-96 overflow-y-auto space-y-2 bg-gray-50">
        {messages.map((msg, idx) => (
          <div
            key={idx}
            className={`p-2 rounded max-w-[70%] ${
              msg.from === "bot"
                ? "bg-gray-200 text-left"
                : "bg-blue-500 text-white self-end text-right ml-auto"
            }`}
          >
            {msg.text}
          </div>
        ))}
      </div>
      <form onSubmit={handleSend} className="mt-4 flex gap-2">
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Type your message..."
          className="flex-1 border px-3 py-2 rounded"
        />
        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded"
        >
          Send
        </button>
      </form>
    </div>
  );
};

export default Chat;
