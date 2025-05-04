import React, { useState } from "react";
import Breadcrumbs from "../../components/pageProps/Breadcrumbs";
import Pagination from "../../components/pageProps/shopPage/Pagination";

const Shop = () => {
  const [itemsPerPage] = useState(12);


  return (
<div className="min-h-screen flex flex-col items-center justify-center px-4">
  <Breadcrumbs title="Products" />
  
  {/* ================= Shop =================== */}
  <div className="w-full flex items-center justify-center pb-20">
    <div className="w-full mdl:w-[80%] lgl:w-[75%] flex flex-col gap-10 items-center">
      <Pagination itemsPerPage={itemsPerPage} />
    </div>
  </div>
</div>

  );
};

export default Shop;
