const TransactionColumns = [
  {
    Header: "Transaction Id",
    accessor: "tid",
  },
  {
    Header: "Transaction Type",
    accessor: "transactionType",
  },
  {
    Header: "From Account",
    accessor: "fromAccountNumber",
  },
  {
    Header: "To Account",
    accessor: "toAccountNumber",
  },
  {
    Header: "Transaction Amount",
    accessor: "amount",
  },
  {
    Header: "Transaction Date",
    accessor: "date",
  },
  {
    Header: "Remarks",
    accessor: "remark",
  },
  {
    Header: "Maturity Instructions",
    accessor: "maturityInstructions",
  },
  {
    Header: "Transaction Mode",
    accessor: "transactionMode",
  },
];

export default TransactionColumns;
