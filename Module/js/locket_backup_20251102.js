// ========================================
// Locket Gold Premium - Optimized Version
// ⚡ Performance: Fast & Smooth
// 🔐 Lifetime Premium Unlock
// ========================================

(function() {
  'use strict';
  
  // ========= Constants (Pre-defined) ========= //
  const SPECIFIC_DATE = "2025-07-18T00:00:00Z";
  const EXPIRES_DATE = "2099-07-18T01:04:17Z";
  const EXPIRES_DATE_LONG = "2099-12-18T01:04:17Z";
  const PRODUCT_ID = "locket.premium.yearly";
  
  // ========= Mapping Configuration ========= //
  const APP_MAPPING = {
    '%E8%BD%A6%E7%A5%A8%E7%A5%A8': ['vip+watch_vip', PRODUCT_ID],
    'Locket': ['Gold', PRODUCT_ID]
  };
  
  // ========= Get User-Agent (Optimized) ========= //
  const headers = $request.headers;
  const ua = headers["User-Agent"] || headers["user-agent"] || "";
  
  // ========= Parse Response Body (Fast Parse) ========= //
  let responseObj;
  try {
    responseObj = JSON.parse($response.body);
    
    // Ensure structure exists (Fast initialization)
    if (!responseObj.subscriber) {
      responseObj.subscriber = {};
    }
    if (!responseObj.subscriber.subscriptions) {
      responseObj.subscriber.subscriptions = {};
    }
    if (!responseObj.subscriber.entitlements) {
      responseObj.subscriber.entitlements = {};
    }
  } catch (error) {
    // Fast error recovery
    responseObj = {
      subscriber: {
        subscriptions: {},
        entitlements: {}
      }
    };
  }
  
  // ========= Premium Data (Pre-built for speed) ========= //
  const subscriptionData = {
    is_sandbox: false,
    ownership_type: "PURCHASED",
    billing_issues_detected_at: null,
    period_type: "normal",
    expires_date: EXPIRES_DATE,
    grace_period_expires_date: null,
    unsubscribe_detected_at: null,
    original_purchase_date: SPECIFIC_DATE,
    purchase_date: SPECIFIC_DATE,
    store: "app_store"
  };
  
  const entitlementData = {
    grace_period_expires_date: null,
    purchase_date: SPECIFIC_DATE,
    product_identifier: PRODUCT_ID,
    expires_date: EXPIRES_DATE_LONG
  };
  
  // ========= Apply Mapping (Optimized Logic) ========= //
  let matched = false;
  
  // Fast mapping lookup
  for (const key in APP_MAPPING) {
    if (ua.indexOf(key) !== -1) {
      const [entitlementKey, subscriptionKey] = APP_MAPPING[key];
      responseObj.subscriber.subscriptions[subscriptionKey] = subscriptionData;
      responseObj.subscriber.entitlements[entitlementKey] = entitlementData;
      matched = true;
      break;
    }
  }
  
  // Default fallback (if no match)
  if (!matched) {
    responseObj.subscriber.subscriptions[PRODUCT_ID] = subscriptionData;
    responseObj.subscriber.entitlements["Gold"] = entitlementData;
  }
  
  // ========= Return Response (Fast stringify) ========= //
  $done({ body: JSON.stringify(responseObj) });
  
})();
