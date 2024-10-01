interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  label: string;
}

export function Button(props: ButtonProps) {
  return (
    <button
      className={`min-w-28 rounded-sm px-4 py-1 text-white ${props.className}`}
      onClick={props.onClick}
    >
      {props.label}
    </button>
  );
}
