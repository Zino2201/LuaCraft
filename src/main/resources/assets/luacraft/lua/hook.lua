-- Hook library
-- Based from GMod's one

local IsString = IsString

local Hooks = {}
hook = {}

function hook.Add(event, name, func)
    if (Hooks[event] == nil) then
        Hooks[event] = {}
    end

    Hooks[event][name] = func
end

function hook.Call(event, ...)
    local hooktable = Hooks[event]

    if not (hooktable == nil) then
        local a, b, c, d, e, f

        for k, v in pairs(hooktable) do
            if IsString(k) then
                a, b, c, d, e, f = v(...)
            else
                a, b, c, d, e, f = v( k, ... )
            end

            if not (a == nil) then
                return a, b, c, d, e, f
            end
        end
    end
end