import React from 'react';

export default function AccountListPage() {
  // --- Mock data -----------------------------------------------------------
  const [accounts, setAccounts] = React.useState(() => [
    { id: 1, alias: "월급통장", accountName: "MyBank 통장쏙", bank: "MyBank", number: "3333-12-345678", type: "입출금", balance: 3820450, currency: "KRW", status: "정상", favorite: true, lastActivityTs: Date.now() - 1000 * 60 * 60 * 2, lastActivity: "09/28 급여입금 3,000,000" },
    { id: 2, alias: "비상금", accountName: "우리 드림샘", bank: "우리", number: "1002-455-991102", type: "입출금", balance: 550000, currency: "KRW", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 24 * 1, lastActivity: "09/29 편의점 5,500" },
    { id: 3, alias: "적금 12M", accountName: "국민 펩스 12M", bank: "KB국민", number: "8888-44-221100", type: "적금", balance: 2400000, currency: "KRW", status: "정상", favorite: true, lastActivityTs: Date.now() - 1000 * 60 * 60 * 26, lastActivity: "09/29 자동이체 200,000" },
    { id: 4, alias: "외화USD", accountName: "하나 FX 자유", bank: "하나", number: "920-111-555555", type: "외화", balance: 1240.55, currency: "USD", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 8, lastActivity: "09/30 매수 200 USD" },
    { id: 5, alias: "청약예금", accountName: "신한 청약통장", bank: "신한", number: "110-222-333444", type: "예금", balance: 300000, currency: "KRW", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 48, lastActivity: "09/28 이자지급 550" },
    { id: 6, alias: "주식 CMA", accountName: "NH투자 CMA", bank: "NH농협", number: "790-11-223344", type: "증권", balance: 17500450, currency: "KRW", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 12, lastActivity: "09/30 매도 1,200,000" },
    { id: 7, alias: "적금 24M", accountName: "신한 하이하이 24M", bank: "신한", number: "110-555-666777", type: "적금", balance: 3600000, currency: "KRW", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 72, lastActivity: "09/27 자동이체 150,000" },
    { id: 8, alias: "생활비", accountName: "국민 리브", bank: "KB국민", number: "5555-22-991133", type: "입출금", balance: 81530, currency: "KRW", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 5, lastActivity: "09/30 마트 62,400" },
    { id: 9, alias: "장기예금", accountName: "우리 든든 12M", bank: "우리", number: "1002-888-000999", type: "예금", balance: 10000000, currency: "KRW", status: "휴면", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 24 * 60, lastActivity: "08/01 만기연장" },
    { id: 10, alias: "여행USD", accountName: "하나 Global", bank: "하나", number: "920-333-999000", type: "외화", balance: 305.12, currency: "USD", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 16, lastActivity: "09/30 카드결제 25 USD" },
    { id: 11, alias: "연금저축", accountName: "NH 연금저축", bank: "NH농협", number: "790-22-445566", type: "증권", balance: 8250040, currency: "KRW", status: "정상", favorite: true, lastActivityTs: Date.now() - 1000 * 60 * 60 * 36, lastActivity: "09/29 매수 300,000" },
    { id: 12, alias: "목돈예금", accountName: "MyBank 안정형 6M", bank: "MyBank", number: "3333-77-888999", type: "예금", balance: 1000000, currency: "KRW", status: "정상", favorite: false, lastActivityTs: Date.now() - 1000 * 60 * 60 * 20, lastActivity: "09/30 이자지급 1,200" },
  ]);

  // --- UI/Filter states ----------------------------------------------------
  const [query, setQuery] = React.useState("");
  const [type, setType] = React.useState("전체");
  const [selectedBanks, setSelectedBanks] = React.useState(new Set());
  const [minBal, setMinBal] = React.useState("");
  const [maxBal, setMaxBal] = React.useState("");
  const [statuses, setStatuses] = React.useState(new Set()); // ex) "정상","휴면"
  const [sortKey, setSortKey] = React.useState("recent"); // recent | balDesc | balAsc | name

  const bankOptions = ["MyBank", "신한", "KB국민", "우리", "하나", "NH농협"];
  const typeOptions = ["전체", "입출금", "예금", "적금", "증권", "외화"];

  // --- Utils ---------------------------------------------------------------
  const won = (n) => n.toLocaleString('ko-KR');
  const formatCurrency = (amt, cur) => cur === 'USD' ? `$ ${amt.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}` : `₩ ${won(amt)}`;
  const maskAcc = (s) => s.replace(/(\d{2,4})-(\d{2,4})-(\d{2,6})/, (__, a, b, c) => `${a}-${b}-` + c.replace(/\d/g, '•'));

  function toggleBank(b) {
    const next = new Set(selectedBanks);
    next.has(b) ? next.delete(b) : next.add(b);
    setSelectedBanks(next);
  }
  function toggleStatus(s) {
    const next = new Set(statuses);
    next.has(s) ? next.delete(s) : next.add(s);
    setStatuses(next);
  }
  function resetFilters() {
    setQuery(""); setType("전체"); setSelectedBanks(new Set()); setMinBal(""); setMaxBal(""); setStatuses(new Set()); setSortKey("recent");
  }
  function copyText(t) { navigator.clipboard?.writeText(t); }

  function downloadCsv(rows) {
    const header = ["별칭", "계좌명", "은행", "계좌번호", "유형", "잔액", "통화", "상태", "최근거래"];
    const body = rows.map(r => [r.alias, r.accountName, r.bank, r.number, r.type, r.balance, r.currency, r.status, r.lastActivity]);
    const csv = [header, ...body].map(r => r.map(v => `"${String(v).replaceAll('"', '""')}"`).join(',')).join('\n');
    const blob = new Blob(["\uFEFF" + csv], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a'); a.href = url; a.download = 'accounts.csv'; a.click(); URL.revokeObjectURL(url);
  }

  // --- Derived list --------------------------------------------------------
  const filtered = React.useMemo(() => {
    return accounts.filter(a => {
      // query
      const q = query.trim().toLowerCase();
      const okQ = !q || [a.alias, a.accountName, a.number].some(s => s.toLowerCase().includes(q));
      if (!okQ) return false;
      // type
      if (type !== "전체" && a.type !== type) return false;
      // bank
      if (selectedBanks.size > 0 && !selectedBanks.has(a.bank)) return false;
      // status
      if (statuses.size > 0 && !statuses.has(a.status)) return false;
      // balance range (KRW only for range; foreign currency skip)
      const balKRW = a.currency === 'KRW' ? a.balance : null;
      if (minBal !== "" && balKRW != null && balKRW < Number(minBal)) return false;
      if (maxBal !== "" && balKRW != null && balKRW > Number(maxBal)) return false;
      return true;
    }).sort((a, b) => {
      switch (sortKey) {
        case 'balDesc': return (toKRW(b) - toKRW(a));
        case 'balAsc': return (toKRW(a) - toKRW(b));
        case 'name': return (a.alias || a.accountName).localeCompare(b.alias || b.accountName, 'ko');
        default: return (b.lastActivityTs - a.lastActivityTs); // recent
      }
    });
  }, [accounts, query, type, selectedBanks, minBal, maxBal, statuses, sortKey]);

  function toKRW(a) { return a.currency === 'KRW' ? a.balance : a.balance * 1350; } // rough mock FX for sort only
  const totalCount = filtered.length;

  // --- Dev checks (lightweight tests) -------------------------------------
  function devChecks(list) {
    try {
      console.assert(maskAcc('1234-56-789012') === '1234-56-••••••', 'maskAcc should mask last segment');
      console.assert(formatCurrency(1000, 'KRW').startsWith('₩ '), 'KRW currency format');
      console.assert(formatCurrency(12.34, 'USD').startsWith('$ '), 'USD currency format');
      console.assert(Math.abs(toKRW({ balance: 10, currency: 'USD' }) - 13500) < 1e-6, 'toKRW USD->KRW');
      console.assert(toKRW({ balance: 13500, currency: 'KRW' }) === 13500, 'toKRW KRW passthrough');
      console.assert(Array.isArray(list), 'filtered should be an array');
    } catch (e) { console.error('Dev checks failed', e); }
  }
  React.useEffect(() => { devChecks(filtered); }, [filtered]);

  // --- Small reusable bits -------------------------------------------------
  const Chip = ({ active, children, onClick }) => (
    <button onClick={onClick} className={("px-3 py-1.5 rounded-full text-xs border transition " + (active ? "bg-blue-50 text-blue-700 border-blue-300" : "hover:bg-gray-50"))}>{children}</button>
  );

  const Toggle = ({ checked, onChange, label }) => (
    <label className="inline-flex items-center gap-2 select-none cursor-pointer">
      <input type="checkbox" className="rounded border-gray-300" checked={checked} onChange={e => onChange(e.target.checked)} />
      <span className="text-sm text-gray-700">{label}</span>
    </label>
  );

  // --- Render --------------------------------------------------------------
  return (
    <div className="min-h-screen bg-white text-gray-900">
      <a href="#main" className="sr-only focus:not-sr-only focus:absolute focus:top-2 focus:left-2 bg-blue-700 text-white px-3 py-2 rounded">본문 바로가기</a>

      {/* Header */}
      <header className="sticky top-0 z-40 w-full bg-white/90 backdrop-blur supports-[backdrop-filter]:bg-white/80 border-b">
        <div className="hidden md:flex h-10 items-center justify-between px-6 text-sm text-gray-600">
          <div className="flex items-center gap-4">
            <a href="#" className="hover:underline">공지</a>
            <a href="#" className="hover:underline">접근성</a>
            <a href="#" className="hover:underline">고객센터</a>
          </div>
          <div className="flex items-center gap-4">
            <button className="hover:underline" aria-label="언어 전환">KO/EN</button>
            <button className="relative" aria-label="알림">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" className="text-gray-700"><path d="M12 22a2 2 0 0 0 2-2h-4a2 2 0 0 0 2 2Zm6-6V11a6 6 0 1 0-12 0v5l-2 2v1h16v-1l-2-2Z" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" /></svg>
              <span className="absolute -top-1 -right-1 bg-red-500 text-white text-[10px] leading-none rounded-full px-1">3</span>
            </button>
          </div>
        </div>
        <div className="h-16 flex items-center justify-between px-6">
          <div className="flex items-center gap-8">
            <a href="/" className="font-semibold text-xl text-blue-700 tracking-tight">MyBank</a>
            <nav className="hidden lg:flex items-center gap-6 text-sm text-gray-700">
              <a href="#" className="hover:text-blue-700">개인</a>
              <a href="#" className="hover:text-blue-700">상품</a>
              <a href="#" className="hover:text-blue-700">대출</a>
              <a href="#" className="hover:text-blue-700">외환</a>
              <a href="#" className="hover:text-blue-700">이벤트</a>
            </nav>
          </div>
          <div className="flex items-center gap-3">
            <label className="relative hidden md:block">
              <input value={query} onChange={e => setQuery(e.target.value)} className="peer w-64 rounded-full border border-gray-300 pl-10 pr-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-600" placeholder="계좌/별칭/번호 검색" />
              <span className="absolute left-3 top-2.5 text-gray-500">
                <svg width="20" height="20" viewBox="0 0 24 24" fill="none"><path d="M21 21l-4.35-4.35M10 17a7 7 0 1 1 0-14 7 7 0 0 1 0 14Z" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round" /></svg>
              </span>
            </label>
            <button className="rounded-full bg-blue-700 text-white px-5 py-2 text-sm hover:bg-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-600">로그인/인증</button>
          </div>
        </div>
      </header>

      <main id="main" className="bg-gray-50">
        {/* Title row */}
        <section className="border-b bg-white">
          <div className="mx-auto max-w-screen-xl px-6 py-6">
            <div className="flex items-center justify-between">
              <div>
                <h1 className="text-xl md:text-2xl font-semibold tracking-tight">계좌 목록 조회</h1>
                <p className="text-sm text-gray-600 mt-1">보유 중인 계좌를 한눈에 확인하고, 빠르게 이체/관리하세요.</p>
              </div>
              <div className="flex items-center gap-2">
                <button className="rounded-full border px-4 py-2 text-sm hover:bg-gray-50">계좌개설</button>
                <button className="rounded-full border px-4 py-2 text-sm hover:bg-gray-50">이체하기</button>
              </div>
            </div>
          </div>
        </section>

        {/* Layout */}
        <section>
          <div className="mx-auto max-w-screen-xl px-6 py-6">
            <div className="grid grid-cols-12 gap-6">
              {/* Filters */}
              <aside className="col-span-12 md:col-span-3">
                <div className="rounded-2xl border bg-white p-4 md:p-5 shadow-sm">
                  <div className="flex items-center justify-between mb-3">
                    <h2 className="text-sm font-semibold text-gray-800">검색 / 필터</h2>
                    <button onClick={resetFilters} className="text-xs text-gray-500 hover:underline">초기화</button>
                  </div>

                  <label className="block text-xs text-gray-600 mb-1">계좌/별칭 검색</label>
                  <input value={query} onChange={e => setQuery(e.target.value)} type="text" placeholder="예) 월급통장, 3333-****"
                    className="w-full rounded-lg border px-3 py-2 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-600" />

                  <div className="mt-4">
                    <div className="text-xs text-gray-600 mb-1">계좌 유형</div>
                    <div className="flex flex-wrap gap-2">
                      {typeOptions.map(t => (
                        <Chip key={t} active={type === t} onClick={() => setType(t)}>{t}</Chip>
                      ))}
                    </div>
                  </div>

                  <div className="mt-4">
                    <div className="text-xs text-gray-600 mb-1">은행</div>
                    <div className="grid grid-cols-2 gap-2 text-xs">
                      {bankOptions.map(b => (
                        <label key={b} className="inline-flex items-center gap-2"><input type="checkbox" className="rounded border-gray-300" checked={selectedBanks.has(b)} onChange={() => toggleBank(b)} /> {b}</label>
                      ))}
                    </div>
                  </div>

                  <div className="mt-4">
                    <div className="text-xs text-gray-600 mb-1">잔액 범위 (₩)</div>
                    <div className="flex items-center gap-2">
                      <input value={minBal} onChange={e => setMinBal(e.target.value)} type="number" placeholder="최소" className="w-full rounded-lg border px-3 py-2 text-sm" />
                      <span className="text-gray-400">~</span>
                      <input value={maxBal} onChange={e => setMaxBal(e.target.value)} type="number" placeholder="최대" className="w-full rounded-lg border px-3 py-2 text-sm" />
                    </div>
                  </div>

                  <div className="mt-4">
                    <div className="text-xs text-gray-600 mb-1">상태</div>
                    <div className="flex flex-wrap gap-2 text-xs">
                      {['정상', '휴면', '해지예정'].map(s => (
                        <Chip key={s} active={statuses.has(s)} onClick={() => toggleStatus(s)}>{s}</Chip>
                      ))}
                    </div>
                  </div>

                  <div className="mt-5">
                    <button className="w-full rounded-lg bg-blue-700 text-white py-2.5 text-sm hover:bg-blue-800 focus:outline-none focus:ring-2 focus:ring-blue-600">조회</button>
                  </div>
                </div>
              </aside>

              {/* Results */}
              <section className="col-span-12 md:col-span-9">
                <div className="rounded-2xl border bg-white p-4 md:p-5 shadow-sm">
                  {/* Toolbar */}
                  <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-3">
                    <div className="flex items-center gap-3 text-sm">
                      <span className="text-gray-600">총 <b className="text-gray-900">{totalCount}</b>개 계좌</span>
                      <span className="hidden md:inline-block w-px h-4 bg-gray-200"></span>
                      <div className="flex items-center gap-2">
                        <label className="text-gray-600">정렬</label>
                        <select value={sortKey} onChange={e => setSortKey(e.target.value)} className="rounded-md border px-2.5 py-1.5 text-sm">
                          <option value="recent">최근 사용순</option>
                          <option value="balDesc">잔액 높은순</option>
                          <option value="balAsc">잔액 낮은순</option>
                          <option value="name">이름순</option>
                        </select>
                      </div>
                    </div>
                    <div className="flex items-center gap-2">
                      <button onClick={() => downloadCsv(filtered)} className="rounded-lg border px-3 py-2 text-sm hover:bg-gray-50">CSV 다운로드</button>
                      <button onClick={() => setAccounts(a => [...a])} className="rounded-lg border px-3 py-2 text-sm hover:bg-gray-50">목록 새로고침</button>
                    </div>
                  </div>

                  {/* Table (desktop) */}
                  <div className="mt-4 overflow-auto hidden md:block">
                    <table className="min-w-[760px] w-full text-sm">
                      <thead className="text-gray-600">
                        <tr className="border-b bg-gray-50">
                          <th className="text-left font-medium px-3 py-2">별칭 / 계좌명</th>
                          <th className="text-left font-medium px-3 py-2">은행</th>
                          <th className="text-left font-medium px-3 py-2">계좌번호</th>
                          <th className="text-right font-medium px-3 py-2">잔액</th>
                          <th className="text-left font-medium px-3 py-2">최근 거래</th>
                          <th className="text-center font-medium px-3 py-2">상태</th>
                          <th className="text-center font-medium px-3 py-2">즐겨찾기</th>
                          <th className="text-center font-medium px-3 py-2">액션</th>
                        </tr>
                      </thead>
                      <tbody className="divide-y">
                        {filtered.map(row => (
                          <tr key={row.id} className="hover:bg-gray-50">
                            <td className="px-3 py-3">
                              <div className="font-medium text-gray-900">{row.alias}</div>
                              <div className="text-gray-500 text-xs">{row.accountName} · {row.type}</div>
                            </td>
                            <td className="px-3 py-3">{row.bank}</td>
                            <td className="px-3 py-3">
                              <div className="flex items-center gap-2">
                                <span className="font-mono tracking-wider">{maskAcc(row.number)}</span>
                                <button onClick={() => copyText(row.number)} className="text-xs text-gray-500 hover:text-gray-700 underline">복사</button>
                              </div>
                            </td>
                            <td className="px-3 py-3 text-right">
                              <span className="font-medium">{formatCurrency(row.balance, row.currency)}</span>
                            </td>
                            <td className="px-3 py-3 text-gray-600">{row.lastActivity}</td>
                            <td className="px-3 py-3 text-center">
                              <span className={"inline-flex items-center rounded-full px-2.5 py-1 text-xs font-medium " + (row.status === "정상" ? "bg-emerald-50 text-emerald-700" : "bg-amber-50 text-amber-700")}>{row.status}</span>
                            </td>
                            <td className="px-3 py-3 text-center">
                              <button onClick={() => setAccounts(list => list.map(a => a.id === row.id ? { ...a, favorite: !a.favorite } : a))} aria-label="즐겨찾기">
                                {row.favorite ? (
                                  <svg width="18" height="18" viewBox="0 0 24 24" fill="#f59e0b"><path d="M12 .587l3.668 7.431 8.2 1.193-5.934 5.786 1.402 8.168L12 18.897l-7.336 3.868 1.402-8.168L.132 9.211l8.2-1.193L12 .587z" /></svg>
                                ) : (
                                  <svg width="18" height="18" viewBox="0 0 24 24" fill="none"><path d="M12 17.75l-6.16 3.24 1.18-6.88L1 9.51l6.9-1 3.1-6.28 3.1 6.28 6.9 1-5.02 4.6 1.18 6.88L12 17.75z" stroke="#9ca3af" strokeWidth="1.5" fill="none" /></svg>
                                )}
                              </button>
                            </td>
                            <td className="px-3 py-3 text-center">
                              <div className="flex justify-center gap-1.5">
                                <button className="px-2.5 py-1.5 rounded-md border text-xs hover:bg-gray-50">이체</button>
                                <button className="px-2.5 py-1.5 rounded-md border text-xs hover:bg-gray-50">상세</button>
                              </div>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>

                  {/* Cards (mobile) */}
                  <div className="md:hidden space-y-3 mt-3">
                    {filtered.map(row => (
                      <div key={row.id} className="rounded-xl border p-3">
                        <div className="flex items-start justify-between">
                          <div>
                            <div className="text-sm text-gray-500">{row.bank} · {row.type}</div>
                            <div className="font-medium text-gray-900">{row.alias}</div>
                          </div>
                          <button onClick={() => setAccounts(list => list.map(a => a.id === row.id ? { ...a, favorite: !a.favorite } : a))} aria-label="즐겨찾기">
                            {row.favorite ? (
                              <svg width="18" height="18" viewBox="0 0 24 24" fill="#f59e0b"><path d="M12 .587l3.668 7.431 8.2 1.193-5.934 5.786 1.402 8.168L12 18.897l-7.336 3.868 1.402-8.168L.132 9.211l8.2-1.193L12 .587z" /></svg>
                            ) : (
                              <svg width="18" height="18" viewBox="0 0 24 24" fill="none"><path d="M12 17.75l-6.16 3.24 1.18-6.88L1 9.51l6.9-1 3.1-6.28 3.1 6.28 6.9 1-5.02 4.6 1.18 6.88L12 17.75z" stroke="#9ca3af" strokeWidth="1.5" fill="none" /></svg>
                            )}
                          </button>
                        </div>
                        <div className="mt-2 flex items-center justify-between">
                          <div className="font-semibold">{formatCurrency(row.balance, row.currency)}</div>
                          <div className="text-xs text-gray-500">{maskAcc(row.number)}</div>
                        </div>
                        <div className="mt-2 text-xs text-gray-600">{row.lastActivity}</div>
                        <div className="mt-3 flex items-center justify-between">
                          <span className={"inline-flex items-center rounded-full px-2.5 py-1 text-xs font-medium " + (row.status === "정상" ? "bg-emerald-50 text-emerald-700" : "bg-amber-50 text-amber-700")}>{row.status}</span>
                          <div className="flex gap-1.5">
                            <button className="px-2.5 py-1.5 rounded-md border text-xs">이체</button>
                            <button className="px-2.5 py-1.5 rounded-md border text-xs">상세</button>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>

                  {filtered.length === 0 && (
                    <div className="text-center text-sm text-gray-500 py-12">조건에 맞는 계좌가 없습니다.</div>
                  )}
                </div>
              </section>
            </div>
          </div>
        </section>
      </main>

      {/* Footer */}
      <footer className="border-t bg-white">
        <div className="mx-auto max-w-screen-xl px-6 py-10">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-6 text-sm">
            <div>
              <div className="font-semibold mb-2">고객센터</div>
              <ul className="space-y-1 text-gray-600">
                <li><a href="#" className="hover:underline">자주 묻는 질문</a></li>
                <li><a href="#" className="hover:underline">1:1 문의</a></li>
                <li><a href="#" className="hover:underline">영업점 안내</a></li>
              </ul>
            </div>
            <div>
              <div className="font-semibold mb-2">보안/약관</div>
              <ul className="space-y-1 text-gray-600">
                <li><a href="#" className="hover:underline">전자금융거래약관</a></li>
                <li><a href="#" className="hover:underline">개인정보처리방침</a></li>
                <li><a href="#" className="hover:underline">보호금융상품등록부</a></li>
              </ul>
            </div>
            <div>
              <div className="font-semibold mb-2">회사</div>
              <ul className="space-y-1 text-gray-600">
                <li><a href="#" className="hover:underline">회사소개</a></li>
                <li><a href="#" className="hover:underline">채용</a></li>
                <li><a href="#" className="hover:underline">공지사항</a></li>
              </ul>
            </div>
            <div>
              <div className="font-semibold mb-2">소셜</div>
              <ul className="space-y-1 text-gray-600">
                <li><a href="#" className="hover:underline">YouTube</a></li>
                <li><a href="#" className="hover:underline">Instagram</a></li>
                <li><a href="#" className="hover:underline">X(Twitter)</a></li>
              </ul>
            </div>
          </div>
          <div className="mt-8 text-xs text-gray-500">© {new Date().getFullYear()} MyBank. All rights reserved.</div>
        </div>
      </footer>
    </div>
  );
}
